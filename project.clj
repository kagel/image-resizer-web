(defproject image-resizer-web "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :min-lein-version "2.0.0"
            :uberjar-name "image-resizer-web.jar"
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [compojure "1.3.1"]
                           [ring/ring-defaults "0.1.2"]
                           [ring/ring-jetty-adapter "1.3.2"]
                           [image-resizer "0.1.6"]]
            :plugins [[lein-ring "0.8.13"]]
            :ring {:handler image-resizer-web.handler/app}
            :profiles
            {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
