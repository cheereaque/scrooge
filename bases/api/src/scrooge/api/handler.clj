(ns scrooge.api.handler
  (:require
    [ring.util.response :as r]))

(defn health
  [& _context]
  (r/response "ok"))

