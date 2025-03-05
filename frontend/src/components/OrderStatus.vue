<template>
    <div>
        <v-card-title>
            OrderStatus
        </v-card-title>
        <v-card-text>
            <div v-if="editMode">
                <v-select
                        v-model="newValue"
                        :label="'name'"
                        :items="orderStatusList"
                ></v-select>
            </div>
            <div v-else>
                OrderStatus : {{ value }}
            </div>
        </v-card-text>
    </div>
</template>

<script>
    export default {
        name: 'OrderStatus',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
            orderStatusList : [ "ORDERPLACED",  "COOKED",  "COMPLETED", ],
            newValue: '',
        }),
        async created() {
            if(!this.value) {
                this.newValue = this.orderStatusList[0];
            } else {
                this.newValue = this.value;
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },
        methods: {
        }
    }
</script>

