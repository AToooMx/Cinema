package com.clevercinema.controllers;

import com.clevercinema.dto.SearchDto;
import com.clevercinema.model.*;
import com.clevercinema.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/movies")
@Controller
@AllArgsConstructor
public class AdminMovieController {

    private final MovieService movieService;

    @GetMapping
    public String showMovies(@ModelAttribute("searchDto") SearchDto search, Model model) {

        List<Movie> movies = new ArrayList<>();
        if (search.getTitle() == null) {
            movies = movieService.findAllMovies();
        } else {
            movies = movieService.findMoviesByTitle(search.getTitle());
        }
        model.addAttribute("movies", movies);

        return "admin-movies-page";
    }

    @GetMapping("{id}")
    public String showMovieInfoPage(@PathVariable("id") int id, Model model) {

        Movie movie = movieService.findMovieById(id);

        model.addAttribute("movie", movie);
        model.addAttribute("genreDto", new Genre());
        model.addAttribute("studioDto", new Studio());
        model.addAttribute("producerDto", new Producer());
        model.addAttribute("countryDto", new Country());

        model.addAttribute("allAgeLimit", movieService.getAllAgeLimit());
        model.addAttribute("allLanguage", movieService.getAllLanguage());
        model.addAttribute("allGenre", movieService.getAllGenre());

        return "admin-movie-page";
    }


    @PostMapping("{id}/save")
    public String saveMovieInfo(@PathVariable("id") int id, @ModelAttribute("movie") Movie movie) {

        System.out.println(movie);

        return "redirect:/admin/movies/" + id;
    }

    @GetMapping("{id}/delete-genre-process/{genreId}")
    public String deleteGenreProcess(@PathVariable("id") int id, @PathVariable("genreId") int genreId) {

        movieService.deleteFilmGenreById(genreId);

        return "redirect:/admin/movies/" + id;
    }

    @PostMapping("{id}/add-genre-process")
    public String addGenreProcess(@PathVariable("id") int id, @ModelAttribute("genreDto") Genre genre) {

        movieService.addGenreForMovie(id, genre.getId());

        return "redirect:/admin/movies/" + id;
    }

    @GetMapping("{id}/delete-studio-process/{studioId}")
    public String deleteStudioProcess(@PathVariable("id") int id, @PathVariable("studioId") int studioId) {

        movieService.deleteFilmStudioById(studioId);

        return "redirect:/admin/movies/" + id;
    }

    @PostMapping("{id}/add-studio-process")
    public String addStudioProcess(@PathVariable("id") int id, @ModelAttribute("studioDto") Studio studio) {
        movieService.addStudioForMovie(id, studio.getName());

        return "redirect:/admin/movies/" + id;
    }

    @GetMapping("{id}/delete-producer-process/{producerId}")
    public String deleteProducerProcess(@PathVariable("id") int id, @PathVariable("producerId") int producerId) {

        movieService.deleteFilmStudioById(producerId);

        return "redirect:/admin/movies/" + id;
    }

    @PostMapping("{id}/add-producer-process")
    public String addProducerProcess(@PathVariable("id") int id, @ModelAttribute("producerDto") Producer producer) {
        //movieService.addStudioForMovie(id, producer.getName());

        return "redirect:/admin/movies/" + id;
    }

    @GetMapping("{id}/seances")
    public String showAddSeancesPage() {

        return "admin-seances-page";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, "title", editor);
        binder.registerCustomEditor(String.class, "originalTitle", editor);
        binder.registerCustomEditor(String.class, "duration", editor);
        binder.registerCustomEditor(String.class, "photoName", editor);
        binder.registerCustomEditor(String.class, "description", editor);
        binder.registerCustomEditor(String.class, "name", editor);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, "startRental", customDateEditor);
        binder.registerCustomEditor(Date.class, "endRental", customDateEditor);
    }
}
