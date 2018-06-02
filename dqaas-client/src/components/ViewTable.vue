<template>
    <div class="container">
<div class="columns">
  <div class="column is-one-third">
    <div class="box">
      <p class="title is-8">{{tableData.name}}</p>
      <p>{{tableData.typeName}}</p>
      <p>{{tableData.entityTypeName}}</p>
    </div>
  </div>
  <div class="column">
    <div class="box">
      <p class="title is-4">Columns</p>
            <table class="table is-striped is-narrow is-hoverable is-fullwidth">
                <thead>
                    <tr>
                        <th>Colum name</th>
                        <th>Datatype</th>
                        <th>DQ Score</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="entry in hiveColumns">
                    <td>
                        {{entry.attributes.name}}
                    </td>
                    <td>
                        {{entry.attributes.type}}
                    </td>
                    <td>
                        Unprocessed
                    </td>
                </tr>
                </tbody>
            </table>
    </div>
  </div>
</div>
    </div>
</template>


<script>
import axios from 'axios'

export default {
  data () {
    return {
      tableData: {},
      errors: [],
      guid: this.$route.params.guid
    }
  },
  created () {
    axios.get('http://localhost:9696/v1/tables/' + this.guid)
         .then(response => {
           this.tableData = response.data
         })
  },
  computed:{
    hiveColumns: function() {
      return this.tableData.referredEntities.filter(function(td){
        var tes = td.typeName === 'hive_column'
        return tes
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

</style>