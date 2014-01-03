Codemotion 2013
===============

[![Still maintained](http://stillmaintained.com/lmivan/codemotion-2013.png)](http://stillmaintained.com/lmivan/codemotion-2013")
[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/lmivan/codemotion-2013/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

This is the demo project for my talk [¡Quiero tiempo real y lo quiero para ayer!](http://codemotion.es/talk/19-october/88) from Codemotion 2013.

The project repo has the following tags:

- fligth_1: Initial configuration. The emails are sent synchronously.
- fligth_2: Changing configuration the emails are sent asynchronously.
- fligth_3: Save additional stats without changing the code, only creating a new service.
- photo: Demo integrating with filesystem without pain and pushing pictures to browsers in real time. Note that you have to configure the path for the generated photos in [Config.groovy](https://github.com/lmivan/codemotion-2013/blob/master/grails-app/conf/Config.groovy#L94) file and also you have to install `image-magick` in your system.
- xmpp: Interact with an XMPP bot to check the weather and the stocks. Note that you have to configure the xmpp connection details in [Config.groovy](https://github.com/lmivan/codemotion-2013/blob/master/grails-app/conf/Config.groovy#L96) file.

The slides of the talk are available at [slideshare](http://www.slideshare.net/ilopmar/quiero-tiempo-real-y-lo-quiero-para-ayer).

You can contact me at lopez.ivan@gmail.com or via twitter [@ilopmar](https://twitter.com/ilopmar)