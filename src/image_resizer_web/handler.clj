(ns image-resizer-web.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as jetty]
            [image-resizer-web.service :refer [image-resize auto-resize resize-to-width resize-to-height force-resize]]))

(defroutes app-routes
  (GET "/resize/:width{[0-9]+}/:height{[0-9]+}/:image" [image width height]
       (image-resize image auto-resize {:width width :height height}))
  (GET "/resize/_/:height{[0-9]+}/:image" [image height]
       (image-resize image resize-to-height height))
  (GET "/resize/:width{[0-9]+}/_/:image" [image width]
       (image-resize image resize-to-width width))
  (GET "/resize!/:width{[0-9]+}/:height{[0-9]+}/:image" [image width height]
       (image-resize image force-resize {:width width :height height}))
  (route/not-found "Not Found")
  (route/resources "/"))

(def app
  (wrap-defaults app-routes site-defaults))

(comment
  (jetty/run-jetty app {:port 3000}))

