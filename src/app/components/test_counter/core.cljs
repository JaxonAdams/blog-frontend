(ns app.components.test-counter.core
  (:require
   [reagent.core :as r]))

(defn counter []
  (let [count (r/atom 0)]
    (fn []
      [:div.container.mt-5
       [:p "You've clicked the button " @count " times."]
       [:button.btn.btn-primary
        {:on-click #(swap! count inc)}
        "Click me"]])))
