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
        jqueryUI: {
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
        jqueryValidate: {
            deps: [
                'jquery'
            ]
        },
        knob: {
            deps: [
                'jquery'
            ]
        },
        appear: {
            deps: [
                'jquery'
            ]
        },
        entuliga: {
            deps: [
                'jquery',
                'knob',
                'appear',
                'modernizr'
            ]
        }
    },
    paths: {
        backbone: 'vendor/backbone/backbone-min',
        jquery: 'vendor/jquery/jquery',
        jquerySerializeObject: 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        jqueryValidate: 'vendor/jquery/validate/jquery.validate.min',
        jqueryUI: 'vendor/jquery/ui/jquery-ui.min',
        knob: 'vendor/jquery/knob/jquery.knob',
        appear: 'vendor/jquery/appear/appear',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation-min',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datepicker: 'vendor/bootstrap/datepicker/bootstrap-datepicker',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        dateformat: 'vendor/date-format/date.format',
        modernizr: 'vendor/modernizr/modernizr',
        entuliga: 'vendor/entuliga/init'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});