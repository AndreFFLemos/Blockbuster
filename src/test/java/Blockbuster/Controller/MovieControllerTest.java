package Blockbuster.Controller;

import Blockbuster.Config.SecurityConfig;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import Blockbuster.BlockbusterApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = BlockbusterApplication.class)
@AutoConfigureMockMvc //this tells Spring to include a MockMvc instance in the test context. Without this, it couldnt find the
// @ autowired mockmvc we told spring to provide and so the mockmvc wasnt properly instantiated and caused multiple UnsatisfiedDependencyExceptions
//after this we also needed to bypass security configurations from Spring Security setup
@Import(SecurityConfig.class)
public class MovieControllerTest {

    private Movie movie;
    private MovieDto movieDto;
    private List<MovieDto> movieDtos;
    @MockBean
    private MovieServiceInterface movieServiceInterface;
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        objectMapper=new ObjectMapper();
        movieDtos=new LinkedList<>();
        movie= new Movie(0,"Rambo","Action",1981,7);
        movieDto= new MovieDto("Rambo","Action",1981,7);
        movieDtos.add(movieDto);

    }

    @Test
    void createMovieTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.post("/api/movie/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.createMovie(movieDto)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void findMovieByIdTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbyid?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.findMovieById(1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void getMovie() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/1");

        when(movieServiceInterface.findMovieById(1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findAllMovies() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.findAllMovies()).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }

    @Test
    void findMoviesByReleaseYear() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbyyear?year=1981")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.findMoviesByYear(1981)).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }

    @Test
    void findMoviesByGenre() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbygenre?genre=Action")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.findMoviesByGenre("Action")).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }

    @Test
    void updateMovie() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.put("/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.updateMovie(movieDto)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void deleteMovie() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.delete("/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

       doNothing().when(movieServiceInterface).deleteMovieById(1);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void movieBeingWatchedTest() throws Exception {

        var requestBuilder=MockMvcRequestBuilders.get("/api/{customerId}/{movieId}",1,1);

        when(movieServiceInterface.movieBeingWatched(1,1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
