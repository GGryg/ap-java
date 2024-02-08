package pl.ug.edu.ap.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ug.edu.ap.project.domain.*;
import pl.ug.edu.ap.project.domain.request.author.CreateAuthorRequest;
import pl.ug.edu.ap.project.domain.request.manga.CreateMangaRequest;
import pl.ug.edu.ap.project.domain.request.review.CreateReviewRequest;
import pl.ug.edu.ap.project.domain.request.user.CreateUserRequest;
import pl.ug.edu.ap.project.repository.*;
import pl.ug.edu.ap.project.service.author.AuthorService;
import pl.ug.edu.ap.project.service.manga.MangaService;
import pl.ug.edu.ap.project.service.review.ReviewService;
import pl.ug.edu.ap.project.service.user.UserService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpApp(@Autowired MangaService mangaService,
                                      @Autowired AuthorService authorService,
                                      @Autowired UserService userService,
                                      @Autowired ReviewService reviewService,
                                      @Autowired GenreRepository genreRepository) {
        return args -> {
            // Create genres
            Genre action = Genre.builder()
                    .genre("action")
                    .build();
            Genre sliceOfLife = Genre.builder()
                    .genre("sliceOfLife")
                    .build();
            Genre horror = Genre.builder()
                    .genre("horror")
                    .build();
            genreRepository.save(action);
            genreRepository.save(sliceOfLife);
            genreRepository.save(horror);

            // Create Users
            CreateUserRequest user1Request = new CreateUserRequest();
            user1Request.setUsername("user1");
            CreateUserRequest user2Request = new CreateUserRequest();
            user2Request.setUsername("user2");
            CreateUserRequest user3Request = new CreateUserRequest();
            user3Request.setUsername("user3");
            User user1 = userService.addUser(user1Request);
            User user2 = userService.addUser(user2Request);
            User user3 = userService.addUser(user3Request);

            // Create Authors
            CreateAuthorRequest author1Request = new CreateAuthorRequest();
            author1Request.setName("Kentarou");
            author1Request.setLastName("Miura");
            CreateAuthorRequest author2Request = new CreateAuthorRequest();
            author2Request.setName("Sui");
            author2Request.setLastName("Ishida");
            CreateAuthorRequest author3Request = new CreateAuthorRequest();
            author3Request.setName("Makoto");
            author3Request.setLastName("Shinkai");
            authorService.addAuthor(author1Request);
            authorService.addAuthor(author2Request);
            authorService.addAuthor(author3Request);

            // Create mangas
            CreateMangaRequest manga1Request = new CreateMangaRequest();
            manga1Request.setTitle("Berserk");
            manga1Request.setAuthorName("Kentarou");
            manga1Request.setAuthorLastName("Miura");
            manga1Request.setChapters(377);
            manga1Request.setFinished(false);
            manga1Request.setDescription("Fighting against all odds");
            manga1Request.setCreatedDate(LocalDate.parse("1985-07-20"));
            CreateMangaRequest manga2Request = new CreateMangaRequest();
            manga2Request.setTitle("Japan");
            manga2Request.setAuthorName("Kentarou");
            manga2Request.setAuthorLastName("Miura");
            manga2Request.setChapters(6);
            manga2Request.setFinished(true);
            manga2Request.setDescription("About yakuza");
            manga2Request.setCreatedDate(LocalDate.parse("1990-12-20"));
            CreateMangaRequest manga3Request = new CreateMangaRequest();
            manga3Request.setTitle("Your name");
            manga3Request.setAuthorName("Makoto");
            manga3Request.setAuthorLastName("Shinkai");
            manga3Request.setChapters(10);
            manga3Request.setFinished(true);
            manga3Request.setDescription("High-schoolers switched bodies");
            manga3Request.setCreatedDate(LocalDate.parse("1985-07-20"));
            CreateMangaRequest manga4Request = new CreateMangaRequest();
            manga4Request.setTitle("Tokyo Ghoul");
            manga4Request.setAuthorName("Sui");
            manga4Request.setAuthorLastName("Ishida");
            manga4Request.setChapters(144);
            manga4Request.setFinished(false);
            manga4Request.setDescription("Surviving in a world of ghouls");
            manga4Request.setCreatedDate(LocalDate.parse("2011-09-08"));
            Manga manga1 = mangaService.addManga(manga1Request);
            Manga manga2 = mangaService.addManga(manga2Request);
            Manga manga3 = mangaService.addManga(manga3Request);
            Manga manga4 = mangaService.addManga(manga4Request);

            // Add genres
            mangaService.addGenre(manga1.getId(), List.of("Action"));
            mangaService.addGenre(manga2.getId(), List.of("Action"));
            mangaService.addGenre(manga3.getId(), List.of("Slice of life", "romance"));
            mangaService.addGenre(manga4.getId(), List.of("Horror"));

            // Create Reviews
            CreateReviewRequest review1Request = new CreateReviewRequest();
            review1Request.setRecommended(true);
            review1Request.setReview("Epic, great art-style.");
            review1Request.setUserId(user1.getId());
            CreateReviewRequest review2Request = new CreateReviewRequest();
            review2Request.setRecommended(false);
            review2Request.setReview("Too brutal");
            review2Request.setUserId(user2.getId());
            CreateReviewRequest review3Request = new CreateReviewRequest();
            review3Request.setRecommended(true);
            review3Request.setReview("Nice");
            review3Request.setUserId(user3.getId());
            reviewService.addReview(manga1.getId(), review1Request);
            reviewService.addReview(manga1.getId(), review2Request);
            reviewService.addReview(manga3.getId(), review3Request);
        };
    }
}
