<div class="sidebar">
    <div class="sidebar" data-active-color="purple" data-background-color="white">
        <div class="logo">
            <img style="max-width: 150px; padding-left: 20px;" src="https://s3.amazonaws.com/craftcellr-images/CRAFTcellr_text.png"/>
        </div>

        <div class="sidebar-wrapper">
            <nav>
                <ul class="nav">
                    <g:render template="/templates/navLink" model="[title: 'Dashboard', icon: 'dashboard', link: '/']"/>
                    <g:render template="/templates/navLink" model="[title: 'Home', icon: 'local_offer', link: '/manageOffer']"/>
                </ul>
            </nav>
        </div>
    </div>
</div>