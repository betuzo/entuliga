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
            'typeahead:select #sel-torneo': 'selToreno'
        },

        initialize: function() {
            this.torneos = new TorneosCollection();
            this.listenTo(this.torneos, 'add', this.agregarTorneo);
            this.listenTo(this.torneos, 'sync', this.syncTorneos);

            this.torneosDesc = [];
            this.torneo = null;

            this.torneos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarTorneo: function(modelo) {
            this.torneosDesc.push({clave: modelo.get('clave'), descripcion: modelo.get('descripcion')});
        },

        syncTorneos: function() {
            this.setUp();
        },

        setUp: function() {
            var numbers = new Bloodhound({
                datumTokenizer: function(d) {
                    return Bloodhound.tokenizers.whitespace(d.descripcion);
                },
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                local: this.torneosDesc
            });

            // initialize the bloodhound suggestion engine
            numbers.initialize();

            // instantiate the typeahead UI
            $('#sel-torneo').typeahead(null, {
                displayKey: 'descripcion',
                source: numbers.ttAdapter()
            });
        },

        selToreno: function(ev, suggestion) {
            this.torneo = suggestion;
        },

        goTorneo: function() {
            if (this.torneo){
                Backbone.history.navigate('torneo/' + this.torneo.clave, { trigger : true });
            }
        }
	});

	return MainView;

});