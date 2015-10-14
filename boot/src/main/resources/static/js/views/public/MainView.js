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

            this.torneosDesc = [];

            this.torneos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarTorneo: function(modelo) {
            this.torneosDesc.push({id: modelo.get('id'), value: modelo.get('descripcion')});
        },

        syncTorneos: function() {
            this.setUp();
        },

        setUp: function() {
            var states = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                local: this.torneosDesc
            });
            states.initialize();
            $('#sel-torneo').typeahead({
                hint: true,
                highlight: true,
                minLength: 1,
                updater: function(item) {
                    alert(item);
                }
            },
            {
                source: states,
                templates: {
                    empty: [
                      '<div class="empty-message">',
                        'unable to find any Best Picture winners that match the current query',
                      '</div>'
                    ].join('\n'),
                    suggestion: _.template('<div><strong><%= value %></strong> – </div>')
                }
            });
        }
	});

	return MainView;

});