package Blockbuster.App.Controller;

import Blockbuster.App.Config.Config;
import Blockbuster.App.DTO.MovieDto;
import Blockbuster.App.Model.Movie;
import Blockbuster.App.Repository.CustomerRepository;
import Blockbuster.App.Repository.MovieRepository;
import Blockbuster.App.Service.MovieService;
import Blockbuster.App.Service.MovieServiceInterface;
import Blockbuster.BlockbusterApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = BlockbusterApplication.class)
public class MovieControllerTest {

    private Movie movie;
    private MovieDto movieDto;
    private List<MovieDto> movieDtos;
    @MockBean
    MovieServiceInterface movieServiceInterface;
    @MockBean
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

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
