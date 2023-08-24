(ns scrooge.api.handler
  (:require
    [ring.util.response :as r]))

(defn health
  "return api health status"
  [& _context]
  (r/response "ok"))

