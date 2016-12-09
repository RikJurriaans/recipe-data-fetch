(defproject recipe-data-fetch "0.1.0-SNAPSHOT"
  :description "A small app that scrapes recipe websites and searches for bodybuilding recipes (high in protein and heigh in carbs)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main recipe-data-fetch.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hickory "0.7.0"]
                 [clj-http "2.3.0"]])
