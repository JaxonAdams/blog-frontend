(ns app.components.blog.post.core
  (:require [reagent.core :as r]
            [promesa.core :as p]
            [app.services.posts.core :as posts-service]))

(defonce post-state (r/atom {:loading true :error nil :html nil}))

(defn fetch-post!
  [id]
  (p/let [post (posts-service/fetch-post id)
          post-html (posts-service/fetch-post-html post)]
    (reset! post-state {:loading false :html post-html})))

(defn blog-post
  [{:keys [path-params]}]
  (let [post-id (:id path-params)]
    (println "POST ID: " post-id)
    (r/create-class
     {:component-did-mount #(fetch-post! post-id)
      :component-did-update #(when-not (:loading @post-state)
                               (js/Prism.highlightAll))
      :reagent-render (fn []
                        (let [{:keys [loading error html]} @post-state]
                          [:div
                           (cond
                             loading [:p "Loading post..."]
                             error [:p.text-red-500 "Error loading post"]
                             html [:div
                                   {:ref (fn [el]
                                           (when (and el html)
                                             (set! (.-innerHTML el) html)))}])]))})))
