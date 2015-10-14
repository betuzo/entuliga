define([
	'jquery',
	'bootstrap',
	'bloodhound',
	'typeahead',
	'core/BaseView',
	'collections/TorneosCollection',
	'text!templates/public/tplMain.html'
], function($, bootstrap, bloodhound, typeahead, BaseView, TorneosCollection, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
            this.torneos = new TorneosCollection();
            this.listenTo(this.torneos, 'add', this.agregarTorneo);
            this.listenTo(this.torneos, 'sync', this.syncTorneos);

            this.torneos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarTorneo: function(modelo) {
        },

        syncTorneos: function() {
            this.setUp();
        },

        setUp: function() {
            var states = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                local: [
                  'Alabama',
                  'Alaska',
                  'Arizona',
                  'Arkansas',
                  'California',
                  'Colorado',
                  'Connecticut',
                  'Delaware',
                  'Florida',
                  'Georgia',
                  'Hawaii',
                  'Idaho',
                  'Illinois',
                  'Indiana',
                  'Iowa',
                  'Kansas',
                  'Kentucky',
                  'Louisiana',
                  'Maine',
                  'Maryland',
                  'Massachusetts',
                  'Michigan',
                  'Minnesota',
                  'Mississippi',
                  'Missouri',
                  'Montana',
                  'Nebraska',
                  'Nevada',
                  'New Hampshire',
                  'New Jersey',
                  'New Mexico',
                  'New York',
                  'North Carolina',
                  'North Dakota',
                  'Ohio',
                  'Oklahoma',
                  'Oregon',
                  'Pennsylvania',
                  'Rhode Island',
                  'South Carolina',
                  'South Dakota',
                  'Tennessee',
                  'Texas',
                  'Utah',
                  'Vermont',
                  'Virginia',
                  'Washington',
                  'West Virginia',
                  'Wisconsin',
                  'Wyoming'
                ]
            });
            states.initialize();
            $('#sel-torneo').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                source: states
            });
        }
	});

	return MainView;

});