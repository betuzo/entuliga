require.config({
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
    },
    paths: {
        backbone: 'vendor/backbone/backbone-min',
        bootstrap : 'vendor/bootstrap/bootstrap.min',
        bootstrapselect : 'vendor/bootstrap/select/bootstrap-select.min',
        jquery: 'vendor/jquery/jquery',
        moment: 'vendor/moment/moment-min',
        momentes: 'vendor/moment/locale/es',
        jquerytimeago: 'vendor/jquery-timeago/jquery.timeago',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});