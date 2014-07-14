(ns tweetbook.models.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as mq]
            [tweetbook.models.config :as config]))

(def db (:db (mg/connect-via-uri config/db-uri)))

(defn insert-msg [msg]
  (mc/insert db "quotes" msg))

(defn list-msgs []
  (println (mc/find-maps db "quotes")))

(defn random-msg []
  (let [cnt (mc/count db "quotes")
        rnd (rand-int cnt)
        {:keys [msg src]} (first (mq/with-collection db "quotes"
                                   (mq/skip rnd)
                                   (mq/limit 1)
                                   (mq/snapshot)))]
    (str msg "\n" src)))
