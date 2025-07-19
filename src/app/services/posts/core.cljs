(ns app.services.posts.core
  (:require [lambdaisland.fetch :as http]
            [promesa.core :as p]))

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

(defn fetch-post
  [post-id]
  (let [uri (str base-url "/api/v1/posts/" post-id)]
    (-> (http/get uri {:accept :json
                       :content-type :json})
        (p/then (fn [resp]
                  (prn {:uri uri :response resp})
                  (-> resp
                      (js->clj :keywordize-keys true)
                      (get-in [:body :data :post]))))
        (p/catch #(prn %)))))

(defn fetch-post-html
  [post]
  (when-let [html-url (:html_post_url post)]
    (-> (http/get html-url {})
        (p/then (fn [resp]
                  (prn {:uri html-url :response resp})
                  (-> resp
                      (js->clj :keywordize-keys true)
                      :body)))
        (p/catch (fn [err] (prn err) "No HTML URL found")))))
