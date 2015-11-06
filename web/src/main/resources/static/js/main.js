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
        bootstrap: {
            deps: [
                'jquery'
            ]
        },
        selecter: {
            deps: [
                'jquery'
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
        },
        bloodhound: {
            deps: [
                'jquery'
            ]
        },
        typeahead: {
            deps: [
                'jquery'
            ]
        },
        fileinput: {
            deps: [
                'jquery',
                'bootstrap'
            ]
        },
        fileinputes: {
            deps: [
                'fileinput',
                'jquery',
                'bootstrap'
           ]
        }
    },
    paths: {
        backbone: 'vendor/backbone/backbone-min',
        jquery: 'vendor/jquery/jquery',
        jquerySerializeObject: 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation-min',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datepicker: 'vendor/bootstrap/datepicker/bootstrap-datepicker',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        fileinput: 'vendor/bootstrap/fileinput/fileinput.min',
        fileinputes: 'vendor/bootstrap/fileinput/fileinput_locale_es',
        dateformat: 'vendor/date-format/date.format',
        bloodhound: 'vendor/typeahead/bloodhound',
        typeahead: 'vendor/typeahead/typeahead.jquery'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});