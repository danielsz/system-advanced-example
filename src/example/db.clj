(ns example.db
  (:require [yesql.core :refer [defquery defqueries]]
            [reloaded.repl :refer [system]]))

(defqueries "sql/queries.sql")

