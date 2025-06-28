(ns app.services.posts.core
  (:require [lambdaisland.fetch :as http]
            [promesa.core :as p]))

;; TODO: set me in environment variables
(def base-url "https://gbiqcyb53k.execute-api.us-west-1.amazonaws.com")

(defn fetch-posts
  [page-size]
  (let [uri (str base-url "/api/v1/posts")]
    (-> (http/get uri {:accept :json
                       :content-type :json
                       :query-params {:pageSize page-size}})
        (p/then (fn [resp]
                  (prn {:uri uri :response resp})
                  (-> resp
                      (js->clj :keywordize-keys true)
                      (get-in [:body :data :posts])
                      (->> (sort-by :modified_at))
                      reverse)))
        (p/catch #(prn %)))))
