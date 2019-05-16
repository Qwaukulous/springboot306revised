package springboot306.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model){
        // first lets create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");
        // now lets create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("about stars...");

        //add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("DeathStarEwoks");
        movie.setYear(2011);
        movie.setDescription("about ewoks on the death ...");
        movies.add(movie);

        //add the list of movies to the directors movie list
        director.setMovies(movies);

        //Save the director to the database
        directorRepository.save(director);

        //grade all the directors from the database and send them to the tmplate

        model.addAttribute("directors", directorRepository.findAll());
        return "index";

    }
}
