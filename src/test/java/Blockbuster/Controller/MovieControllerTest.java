package Blockbuster.Controller;

import Blockbuster.Config.SecurityConfig;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import Blockbuster.BlockbusterApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    @Mock
    private MovieServiceInterface movieServiceInterface;
    @InjectMocks
    private MovieController movieController;
    private final ObjectMapper objectMapper= new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        movieDtos=new LinkedList<>();
        movie= new Movie(0,"Rambo","Action",1981,7);
        movieDto= new MovieDto("Rambo","Action",1981,7);
        movieDtos.add(movieDto);

        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMovieTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.post("/api/movie/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieServiceInterface.createMovie(movieDto)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
        verify(movieServiceInterface).createMovie(movieDto);
    }

    @Test
    void findMovieByIdTest() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbyid?id=1");

        when(movieServiceInterface.findMovieById(1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(movieServiceInterface).findMovieById(1);
    }

    @Test
    void getMovie() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/1");

        when(movieServiceInterface.findMovieById(1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(movieServiceInterface).findMovieById(1);

    }

    @Test
    void findAllMovies() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/all");

        when(movieServiceInterface.findAllMovies()).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(movieServiceInterface).findAllMovies();
    }

    @Test
    void findMoviesByReleaseYear() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbyyear?year=1981");

        when(movieServiceInterface.findMoviesByYear(1981)).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder);

        verify(movieServiceInterface).findMoviesByYear(1981);

    }

    @Test
    void findMoviesByGenre() throws Exception {

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbygenre?genre=Action");

        when(movieServiceInterface.findMoviesByGenre("Action")).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(movieServiceInterface).findMoviesByGenre("Action");

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

        verify(movieServiceInterface).updateMovie(movieDto);

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

        verify(movieServiceInterface).deleteMovieById(1);

    }

    @Test
    void movieBeingWatchedTest() throws Exception {

        var requestBuilder=MockMvcRequestBuilders.get("/api/{customerId}/{movieId}",1,1);

        when(movieServiceInterface.movieBeingWatched(1,1)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(movieServiceInterface).movieBeingWatched(1,1);

    }

}
