(ns app.components.navbar.core)

(defn navbar []
  (fn []
    [:nav.navbar.navbar-expand-lg.navbar-dark.border-bottom.card.mb-4
     [:div.container
      [:a.navbar-brand.text-light {:href "#"} "Jaxon Adams"]
      [:button.navbar-toggler {:type "button" :data-bs-toggle "collapse" :data-bs-target "#navbarNav"}
       [:span.navbar-toggler-icon]]
      [:div#navbarNav.collapse.navbar-collapse
       [:ul.navbar-nav.ms-auto
        [:li.nav-item [:a.nav-link {:href "#"} "Home"]]]]]]))
