<template>
    <div class="container">
            <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Link</th>
                        <th>Title</th>
                    </tr>
                </thead>
                <tr v-for="entry in posts">
                    <td>
                        {{entry.id}}
                    </td>
                    <td>
                        {{entry.link}}
                    </td>
                    <td>
                        {{entry.title}}
                    </td>
                </tr>
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