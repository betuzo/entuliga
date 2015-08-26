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
        backboneValidation: {
            deps: [
                'backbone'
            ]
        },
        jquerySerializeObject: {
            deps: [
                'jquery'
            ]
        },
        datepicker: {
            deps: [
                'jquery'
            ]
        },
        datetimepicker: {
            deps: [
                'jquery'
            ]
        }
    },
    paths: {
        backbone: 'vendor/backbone/backbone-min',
        jquery: 'vendor/jquery/jquery',
        jquerySerializeObject: 'vendor/jquery/jquery.serializeObject/jquery.serializeObject.min',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation-min',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datepicker: 'vendor/bootstrap/datepicker/bootstrap-datepicker',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        dateformat: 'vendor/date-format/date.format'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});