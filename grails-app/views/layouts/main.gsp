<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Grails"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
        <asset:stylesheet src="application.css"/>

        <style type="text/css">
            footer {
                position: absolute;
                left: 0;
                bottom: 0;
                width: 100%;
                height: 10px;
            }
        </style>

        <g:layoutHead/>
    </head>

    <body>

        <div class="wrapper">

            <g:render template="/templates/sidebar"/>

            <div class="main-panel">
                <div class="content">
                    <div class="container-fluid">
                        <g:layoutBody/>
                    </div>
                </div>

                <g:render template="/templates/footer"/>
            </div>
        </div>

        <asset:javascript src="application.js"/>

    </body>
</html>
