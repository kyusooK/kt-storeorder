<template>

    <v-data-table
        :headers="headers"
        :items="orderPage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderPageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderInfo", value: "orderInfo" },
                { text: "requestInfo", value: "requestInfo" },
                { text: "orderStatus", value: "orderStatus" },
                { text: "pickUpLocation", value: "pickUpLocation" },
            ],
            orderPage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/orderPages'))

            temp.data._embedded.orderPages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.orderPage = temp.data._embedded.orderPages;
        },
        methods: {
        }
    }
</script>

