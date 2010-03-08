(ns leiningen.test-is
  (:use [clojure.contrib.java-utils :only [file]]
        [clojure.contrib.find-namespaces :only [find-namespaces-in-dir]]
        [leiningen.test :only [form-for-testing-namespaces]]
        [leiningen.compile :only [eval-in-project]]))

(defn test-is
  "Run the project's tests using clojure.contrib.test-is.
Accepts a list of namespaces for which to run all tests.
If none are given, runs them all."
  [project & namespaces]
  (let [namespaces (if (empty? namespaces)
                     (find-namespaces-in-dir (file (:test-path project)))
                     (map symbol namespaces))]
    (eval-in-project
     project
     (form-for-testing-namespaces
      'clojure.contrib.test-is namespaces))))
