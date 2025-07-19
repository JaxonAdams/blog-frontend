(ns app.components.blog.main.core
  (:require [app.services.posts.core :refer [fetch-posts]]
            [reitit.frontend.easy :as rfe]
            [reagent.core :as r]
            [promesa.core :as p]))

(defonce api-data (r/atom nil))

(defn post-preview [{:keys [title summary href]}]
  [:div.card.mb-4
   [:div.card-body
    [:h2.card-title title]
    [:p.card-text summary]
    [:a.btn.btn-outline-light {:href href} "Read More"]]])

(defn blog-main []
  (r/with-let [_ (-> (fetch-posts 5)
                     (p/then (fn [posts] (println "Fetched posts:" posts) posts))
                     (p/then #(reset! api-data %)))]
    (let [top-posts @api-data]
      [:div.row
       ;; Post Previews — full width on small, 8 cols on medium+
       [:div.col-12.col-md-8
        [:h1.mb-4.p-3.border-bottom "Recent Posts"]
        (if top-posts
          (for [{:keys [id title summary]} top-posts]
            ^{:key id} [post-preview {:title title :summary summary :href (rfe/href :post {:id id})}])
          [:p "Loading posts..."])]

       ;; Sidebar — full width on small, 4 cols on medium+
       [:div.col-12.col-md-4
        [:div.card.text-light.mb-4.mt-5
         [:div.card-body.text-center
          [:img.rounded-circle.mb-3.border.border-primary.border-5
           {:src "/assets/images/profile.png"
            :alt "Jaxon Adams"
            :width 180
            :height 180}]
          [:h5.card-title "About Me"]
          [:p.card-text "Jaxon Adams, the handsome and interesting software engineer. <PLACEHOLDER>"]
          [:a.btn.btn-outline-light
           {:href "https://jaxon-adams.onrender.com/"
            :target "_blank"}
           "My Portfolio"]]]]])))

