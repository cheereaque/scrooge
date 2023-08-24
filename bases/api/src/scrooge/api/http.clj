(ns scrooge.api.http
  (:require
    [integrant.core :as ig]
    [reitit.http :as http]
    [reitit.interceptor.sieppari :as sieppari]
    [ring.adapter.jetty :as jetty]
    [scrooge.api.handler :as h]))

(def ^:private api
  (http/ring-handler
    (http/router
      ["/health" {:get h/health}])

    {:executor sieppari/executor}))

(defmethod ig/halt-key! ::server
  [_ server]
  (println "scrooge.api stopping...")
  (.stop server))

(defmethod ig/init-key ::server
  [_ options]
  (let [port (:port options)]
    (println "scrooge.api running on port 3000")
    (jetty/run-jetty #'api {:port port, :join? false, :async? true})))

