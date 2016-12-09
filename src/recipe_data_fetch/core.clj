(ns recipe-data-fetch.core
  (:require [hickory.core :refer :all]
            [hickory.select :as s]
            [clojure.string :as string]
            [clojure.java.browse :refer :all]))   

(defn make-urls [urls]
  (map #(str "http://www.ah.nl/allerhande/recepten-zoeken/__/" % "?Nrpp=1000") urls)) 

(def keys
  (make-urls
   ["N-26vn"
    "N-26vnZ26vm"
    "N-26vnZ26vmZ26vk"
    "N-26vm"
    "N-26vmZ26vk"
    "N-26vk"
    "N-26vl"
    "N-26vqZ26v8Z26vf/vlees-pasta"
    "N-26vqZ26v8Z26vg/vis-pasta"
    "N-26vqZ26v8Z26vh/gevogelte-pasta"
    "N-26vqZ26v8Z26vh/gevogelte-pasta"
    "N-26vqZ26v8Z26xs/schaal-schelpdieren-pasta"
    "N-26vqZ26v8Z26xt/vleesvervanger-pasta"
    "N-26vqZ26x9/salade"
    "N-26vqZ26v9/rijst"])) ;; TODO: add more urls.

(def data (map #(-> (slurp %) 
                    string/trim-newline
                    string/trim
                    parse
                    as-hickory) urls)) 

(defn get-recipes-from-page [page-hickory]
  (-> (s/select
       (s/child
        (s/and (s/class "item")
               (s/class "recipe"))
        (s/tag :figure)
        (s/tag :a))
       page-hickory))) 

(def extracted-urls (distinct (flatten (map get-recipes-from-page data)))) 

;; It redirects to 1000 records max... dont know how to get it all.
;; We can solve this by mis-using the filter options in the side bar and then later filter out the duplicates.
(count
 (map #(str "www.ah.nl" (-> % :attrs :href)) extracted-urls))  

(def urls (map #(str "www.ah.nl" (-> % :attrs :href)) extracted-urls)) 

(defn -main [& args]
  (println ))
