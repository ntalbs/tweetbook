# TweetBook

This is a simple tweet bot management program written in clojure. Main features are:

* Presents web form to input messages and store them to database
* Pick a random message from the database and tweet it peoridically

## Prerequisites

You will need [Leiningen][1] 2.0.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

Or in REPL:

    (use 'tweetbook.repl)
    (start-server)

## License

Copyright © 2014 FIXME
