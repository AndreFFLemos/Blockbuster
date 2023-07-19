package Blockbuster.Controller;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieService;
import Blockbuster.Service.MovieServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest
public class MovieControllerTest {

    private Movie movie;
    private MovieDto movieDto;
    private List<MovieDto> movieDtos;
    @Mock
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;
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
        //to create the instance that mocks http requests
        mockMvc= MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void createMovieTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.post("/api/movie/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieService.createMovie(movieDto)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void findMovieByIdTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/findbyid?id=0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieService.findMovieById(0)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void getMovie() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieService.findMovieById(0)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void findAllMovies() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/movie/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieService.findAllMovies()).thenReturn(movieDtos);

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

        when(movieService.findMoviesByYear(1981)).thenReturn(movieDtos);

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

        when(movieService.findMoviesByGenre("Action")).thenReturn(movieDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }

    @Test
    void updateMovie() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.put("/api/movie/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(movieService.updateMovie(movieDto)).thenReturn(movieDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    void deleteMovie() throws Exception {
        String requestBody= objectMapper.writeValueAsString(movieDto);

        var requestBuilder= MockMvcRequestBuilders.delete("/api/movie/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

       doNothing().when(movieService).deleteMovieById(0);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
