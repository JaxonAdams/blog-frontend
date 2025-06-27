(ns app.components.blog.main.core)

(defn post-preview [{:keys [title summary href]}]
  [:div.card.mb-4.bg-secondary.text-light
   [:div.card-body
    [:h2.card-title title]
    [:p.card-text summary]
    [:a.btn.btn-outline-light {:href href} "Read More"]]])

(defn blog-main []
  [:div.row
   ;; Post Previews — full width on small, 8 cols on medium+
   [:div.col-12.col-md-8
    [post-preview {:title "First Post"
                   :summary "This is the first post summary."
                   :href "#"}]
    [post-preview {:title "Second Post"
                   :summary "Some more interesting content..."
                   :href "#"}]]

   ;; Sidebar — full width on small, 4 cols on medium+
   [:div.col-12.col-md-4
    [:div.card.bg-dark.text-light.mb-4
     [:div.card-body.text-center
      [:img.rounded-circle.mb-3.border.border-secondary.border-3
       {:src "/assets/images/profile.png"
        :alt "Jaxon Adams"
        :width 220
        :height 220}]
      [:h5.card-title "About Me"]
      [:p.card-text "Jaxon Adams, the handsome and interesting software engineer. <PLACEHOLDER>"]]]
    [:div.mt-4
     [:h5 "Tags"]
     [:div.d-flex.flex-wrap
      [:span.badge.bg-info.me-1.mb-2 "clojure"]
      [:span.badge.bg-info.me-1.mb-2 "software-engineering"]]]]])

