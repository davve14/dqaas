<template>
<div>
  <nav class="navbar is-transparent">
    <div class="navbar-brand">
      <a class="navbar-item" href="/">
        <img src="./assets/Ego_network.png" alt="Atlas" width="50" height="60">
      </a>
      <div class="navbar-burger burger" data-target="navbarExampleTransparentExample">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item" href="/#/browse">
          Browse Business Glossary
        </a>
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link" href="/documentation/overview/start/">
            Create
          </a>
          <div class="navbar-dropdown is-boxed">
            <a class="navbar-item" href="#">
              Governance Policy
            </a>
            <a class="navbar-item" href="/#/createrule">
              Governance Rule
            </a>
          </div>
        </div>
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="field is-grouped">
            <p class="control">
              <a class="bd-tw-button button" data-social-network="Twitter" data-social-action="tweet" data-social-target="http://localhost:4000" target="_blank" href="https://twitter.com/intent/tweet?text=Bulma: a modern CSS framework based on Flexbox&amp;hashtags=bulmaio&amp;url=http://localhost:4000&amp;via=jgthms">
                <span class="icon">
                  <i class="fab fa-twitter"></i>
                </span>
                <span>
                  Tweet
                </span>
              </a>
            </p>
            <p class="control">
              <a class="button is-primary" href="https://github.com/jgthms/bulma/archive/0.5.1.zip">
                <span class="icon">
                  <i class="fas fa-download"></i>
                </span>
                <span>Download</span>
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <div id="app">
    <router-view/>
  </div>
</div>
</template>
<script>

export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2f3b47;
  margin-top: 60px;
}
</style>
