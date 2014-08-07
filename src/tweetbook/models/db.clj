(ns tweetbook.models.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as mq]
            [tweetbook.models.config :as config]))

(defn insert-msg [msg]
  (let [{:keys [conn db]} (mg/connect-via-uri config/db-uri)]
    (mc/insert db "quotes" msg)
    (mg/disconnect conn)))

(defn list-msgs []
  (let [{:keys [conn db]} (mg/connect-via-uri config/db-uri)]
    (println (mc/find-maps db "quotes"))
    (mg/disconnect conn)))

(defn random-msg []
  (let [{:keys [conn db]} (mg/connect-via-uri config/db-uri)
        cnt (mc/count db "quotes")
        rnd (rand-int cnt)
        {:keys [msg src]} (first (mq/with-collection db "quotes"
                                   (mq/skip rnd)
                                   (mq/limit 1)
                                   (mq/snapshot)))]
    (mg/disconnect conn)
    (str msg "\n" src)))
