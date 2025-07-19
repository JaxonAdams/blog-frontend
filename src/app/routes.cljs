(ns app.routes
  (:require [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reagent.core :as r]
            [app.components.blog.main.core :refer [blog-main]]))

(defn about [] [:div [:h1 "About"]]) ; TODO: create a separate component for me

(def routes
  [["/" {:name :home
         :view blog-main}]
   ["/about" {:name :about
              :view about}]])

(defonce current-route (r/atom nil))

(defn init-routes! []
  (rfe/start!
   (rf/router routes)
   (fn [m] (reset! current-route m))
   {:use-fragment false}))

