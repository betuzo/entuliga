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
        marionette:{
            deps: [
                'radio',
                'backbone'
            ],
            exports: 'Mn'
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
        jquerycookie:{
            deps: [
                'jquery'
            ]        },
        jquerySerializeObject: {
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
        fabric: {
            deps: [
                'jquery'
            ]
        }
    },


    paths: {
        backbone: 'vendor/backbone/backbone',
        marionette: 'vendor/backbone/backbone-marionette/backbone.marionette',
        radio: 'vendor/backbone/backbone.radio/backbone.radio',
        jquery: 'vendor/jquery/jquery',
        jquerycookie: 'vendor/jquery/cookie/jquery.cookie',
        jquerySerializeObject: 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        dateformat: 'vendor/date-format/date.format',
        bloodhound: 'vendor/typeahead/bloodhound',
        typeahead: 'vendor/typeahead/typeahead.jquery',
        fabric: 'vendor/fabric/fabric.min'

    }
});


require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});