<template>
    <div class="container">
            <table class="table is-striped is-narrow is-hoverable is-fullwidth">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Owner</th>
                        <th>DQ Score</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="entry in posts">
                    <td><a href="http://www.google.de">
                        {{entry.name}}
                    </a></td>
                    <td>
                        {{entry.typeName}}
                    </td>
                    <td>
                        {{entry.status}}
                    </td>
                    <td>
                        {{entry.owner}}
                    </td>
                    <td>
                        Unprocessed
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
</template>


<script>
import axios from 'axios'

export default {
  data () {
    return {
      posts: [],
      errors: []
    }
  },
  created () {
    axios.get('http://localhost:9696/v1/posts')
         .then(response => {
           this.posts = response.data
         })
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