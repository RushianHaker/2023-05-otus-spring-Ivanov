use library



db.books.insertOne(
{
book_name: "War and Pease",
book_year: 2222,
author: {
    authors_name: "Tolstoy",
    author_year: 1111
    },
genres: [
    {
        genres_name: "history"
    }, {
        genres_name: "love"
    }],
comments: [
    {
        comment_text: "Nice book, tnks!"
    }, {
        comment_text: "I want to see more!"
    }]
})