(ns app.utils.dates)

(defn format-date [epoch-ms]
  (let [date (js/Date. epoch-ms)]
    (.toLocaleDateString date "en-US"
                         #js {:year "numeric"
                              :month "long"
                              :day "numeric"})))
