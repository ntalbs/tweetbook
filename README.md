# TweetBook

This is a simple tweet bot management program written in clojure. Main features are:

* Presents web form to input messages and store them to database
* Pick a random message from the database and tweet it peoridically

## Prerequisites

You will need [Leiningen][1] 2.0.0 or above installed. And before execute the project, you should set up the following environment variables.

    $ export TWEETBOOK_TWITTER_CONSUMER_KEY=...
    $ export TWEETBOOK_TWITTER_CONSUMER_SECRET=...
    $ export TWEETBOOK_TWITTER_ACCESS_TOKEN=...
    $ export TWEETBOOK_TWITTER_ACCESS_TOKEN_SECRET=...
    $ export TWEETBOOK_DB_URI=...
    $ export TWEETBOOK_ID=... #id for login
    $ export TWEETBOOK_PW_HASH=... #password hash for login

Password hash should be generated with `noir.util.crypt/encrypt` function.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

Or in REPL:

    (use 'tweetbook.repl)
    (start-server)

## License

Copyright © 2014 FIXME
