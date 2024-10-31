API Specification:
------------------
getAllMovies
------------------------
GET --- /movies --- Retrieves All movies

Status Code: 200

Response:
---------
[
{
"title": "The Avengers",
"director": "Joss Whedon",
"actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
"releaseYear": "2012",
"description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
"starRating": 0
},
{
"title": "Superman Returns",
"director": "Bryan Singer",
"actors": "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden",
"release": "2006",
"description": "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.",
"rating": 0
},
{
"title": "Steel",
"director": "Kenneth Johnson",
"actors": "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
"release": "1997",
"description": "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
"rating": 0
}
]

getMovieByTitle:
-------------------------------------------------------------------
GET ---- /movies/{title}

Status code: 200

Response:
---------
{
"title": "Steel",
"director": "Kenneth Johnson",
"actors": "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
"release": "1997",
"description": "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
"rating": 0
}

Accept Star Rating and Review:
------------------------------
POST ---- /movies/{title}

Status code: 202

Request:
--------
{
"title":"The Avengers",
"userReviewDTO":
{
"starRating":5,
"review":"Good Movie"

}

Response:
---------
{
"title": "Steel",
"director": "Kenneth Johnson",
"actors": "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
"release": "1997",
"description": "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
"rating": 4.5,
"userReviewDTOs":
[{
"starRating":5,
"review":"Good Movie"
},
{
"starRating":4,
"review":"Good Movie"
}
]
}

User Stories:
-------------
As a user, I should see a list of movies when I visit GMDB.

When I visit GMDB
Then I can see a list of all movies.
As a user, I can browse each movie so I can learn all the details.

Rule: Movie details include title, director, actors, release year, description and star rating.

Given an existing movie
When I visit that title
Then I can see all the movie details.

Given a non-existing movie
When I visit that title
Then I receive a friendly message that it doesn't exist.
As a user, I can give a star rating to a movie so that I can share my experiences with others.

Given an existing movie
When I submit a 5 star rating
Then I can see it in the movie details.

Given a movie with one 5 star rating and one 3 star rating
When I view the movie details
Then I expect the star rating to be 4.
As a user, I can review a movie so that I can share my thoughts about it.

Given an existing movie
When I submit a star rating and text review
Then I can see my contribution on the movie details.

Given an existing movie
When I submit a text review without a star rating
Then I receive a friendly message that a star rating is required.