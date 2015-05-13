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
        jquery: 'vendor/jquery/jquery',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootflat: 'vendor/bootflat/site.min',
        selecter: 'vendor/bootflat/jquery.fs.selecter.min'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});