package dromerof.movies.servicie;

import dromerof.movies.data.MovieRepository;
import dromerof.movies.model.Genre;
import dromerof.movies.model.Movie;
import net.bytebuddy.matcher.ElementMatcher;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static dromerof.movies.model.Genre.*;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceShould {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Before
    public void setUp() {
        movieService = new MovieService(movieRepository);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(
                new Movie(1, "Dark Knight", 152, ACTION),
                new Movie(2, "Memento", 113, THRILLER),
                new Movie(3, "There's Something About Mary", 119, COMEDY),
                new Movie(4, "Super 8", 112, THRILLER),
                new Movie(5, "Scream", 111, HORROR),
                new Movie(6, "Home Alone", 103, COMEDY),
                new Movie(7, "Matrix", 136, ACTION)
        ));
    }

    @Test
    public void returnMoviesByGenre() {
//    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Collection<Movie> movies = movieService.findMoviesByGenre(COMEDY);
        List<Integer> movieIds = movies.stream().map(Movie::getId).collect(Collectors.toList());

        assertThat(movieIds, is(Arrays.asList(3, 6)));
    }

    private void assertThat(List<Integer> movieIds, ElementMatcher.Junction<Object> objectJunction) {
    }
}