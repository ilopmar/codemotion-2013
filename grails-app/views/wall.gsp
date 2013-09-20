<html>
<head>
    <meta name='layout' content='main'/>
    <title>Codemotion 2013</title>

    <r:require module="application"/>
    <r:require module="grailsEvents"/>
</head>

<body>

    <div id="timeline" class="center">
    </div>

    <%-- Handlebars timeline template --%>
    <script id="pic-template" type="text/x-handlebars-template">
        <div class="photo" style="visibility:hidden; height:0;">
            <img src="{{url}}" />
        </div>
    </script>

    <r:script>
        var source = $("#pic-template").html();
        var template = Handlebars.compile(source);

        var baseUrl = "${createLink(uri:'/', absolute:true)}"

        var grailsEvents = new grails.Events(baseUrl);

        grailsEvents.on('photo', function(data) {
            photo = jQuery.parseJSON(data);

            var context = {
                url: baseUrl + "photos/" + photo.url
            }
            var html = template(context);
            $('#timeline').prepend(html);

            $("#timeline .photo:first-child img").on("load", function() {
                $(this).parent().css({
                    display: 'none',
                    visibility: 'visible',
                    height: 'auto'
                });

                $(this).parent().slideDown();
            });
        });
    </r:script>
</body>
</html>