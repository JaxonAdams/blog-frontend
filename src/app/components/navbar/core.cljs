(ns app.components.navbar.core)

(defn navbar []
  (fn []
    [:nav.navbar.navbar-expand-lg.navbar-dark.bg-dark.mb-4
     [:div.container
      [:a.navbar-brand {:href "#"} "Jaxon Adams"]
      [:button.navbar-toggler {:type "button" :data-bs-toggle "collapse" :data-bs-target "#navbarNav"}
       [:span.navbar-toggler-icon]]
      [:div#navbarNav.collapse.navbar-collapse
       [:ul.navbar-nav.ms-auto
        [:li.nav-item [:a.nav-link {:href "#"} "Home"]]]]]]))
