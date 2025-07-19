(ns app.components.blog.post.core
  (:require [reagent.core :as r]
            [promesa.core :as p]
            [app.services.posts.core :as posts-service]
            [app.utils.dates :refer [format-date]]))

(defonce post-state (r/atom {:loading true :error nil :html nil :post-metadata nil}))

(defn fetch-post!
  [id]
  (p/let [post (posts-service/fetch-post id)
          post-html (posts-service/fetch-post-html post)]
    (reset! post-state {:loading false :html post-html :post-metadata post})))

(defn blog-post
  [{:keys [path-params]}]
  (let [post-id (:id path-params)]
    (println "POST ID: " post-id)
    (r/create-class
     {:component-did-mount #(fetch-post! post-id)
      :component-did-update #(when-not (:loading @post-state)
                               (js/Prism.highlightAll))
      :reagent-render (fn []
                        (let [{:keys [loading error html post-metadata]} @post-state]
                          [:div
                           (cond
                             loading [:p "Loading post..."]
                             error [:p.text-red-500 "Error loading post"]
                             html [:<>
                                   [:div.mb-5.border-bottom.pb-3
                                    [:h1.display-5.fw-bold (:title post-metadata)]
                                    [:p.text-muted.fs-5.mb-1 (:summary post-metadata)]
                                    [:ul.list-inline.text-secondary.small.mb-0
                                     [:li.list-inline-item
                                      (str "Written on " (-> post-metadata :created_at format-date))]
                                     [:li.list-inline-item
                                      " | "]
                                     [:li.list-inline-item
                                      (str "Updated on " (-> post-metadata :modified_at format-date))]]
                                    [:div.d-flex.flex-wrap.mt-3
                                     (map (fn [tag] [:span.badge.bg-info.me-1.mb-2.px-3.text-dark tag]) (:tags post-metadata))]]
                                   [:div
                                    {:ref (fn [el]
                                            (when (and el html)
                                              (set! (.-innerHTML el) html)))}]])]))})))
