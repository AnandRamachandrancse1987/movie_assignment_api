DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS links;
DROP TABLE IF EXISTS tags;

CREATE TABLE movies
(
    movieid integer NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    genres text COLLATE pg_catalog."default",
    CONSTRAINT movies_pkey PRIMARY KEY (movieid)
);

CREATE TABLE ratings
(
    userid integer NOT NULL,
    movieid integer NOT NULL,
    rating numeric,
    "timestamp" bigint,
    CONSTRAINT ratings_movie_id_fkey FOREIGN KEY (movieid)
        REFERENCES public.movies (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE links
(
    movieid integer NOT NULL,
    imdbid bigint,
    tmdbid bigint,
    CONSTRAINT links_movie_id_fkey FOREIGN KEY (movieid)
        REFERENCES public.movies (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE tags
(
    userid integer NOT NULL,
    movieid integer NOT NULL,
    tag text COLLATE pg_catalog."default",
    "timestamp" bigint,
    CONSTRAINT tags_movie_id_fkey FOREIGN KEY (movieid)
        REFERENCES public.movies (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);