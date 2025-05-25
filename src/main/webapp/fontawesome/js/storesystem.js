let map;
let markers = [];
let infoWindows = [];

function initMap() {
    // Default center (roughly Hanoi)
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 21.0285, lng: 105.8542 },
        zoom: 12
    });

    // Get all stores from the DOM
    const storeItems = document.querySelectorAll('.store-item');

    // Create markers for each store
    storeItems.forEach((store, index) => {
        const lat = parseFloat(store.getAttribute('data-latitude'));
        const lng = parseFloat(store.getAttribute('data-longitude'));
        const name = store.querySelector('strong').textContent;
        const address = store.querySelector('p').textContent;
        const phone = store.querySelector('.icon-phone').textContent.replace('📞 ', '');

        if (!isNaN(lat) && !isNaN(lng)) {
            // Create marker
            const marker = new google.maps.Marker({
                position: { lat: lat, lng: lng },
                map: map,
                title: name
            });

            // Create info window
            const infoWindow = new google.maps.InfoWindow({
                content: `
                    <div class="info-window">
                        <h3>${name}</h3>
                        <p>${address}</p>
                        <p>📞 ${phone}</p>
                    </div>
                `
            });

            // Add click event to marker
            marker.addListener('click', () => {
                closeAllInfoWindows();
                infoWindow.open(map, marker);
                highlightStore(index);
            });

            markers.push(marker);
            infoWindows.push(infoWindow);
        }
    });

    // Add click events to store items
    storeItems.forEach((store, index) => {
        store.addEventListener('click', () => {
            selectStore(index);
        });
    });
}

function selectStore(index) {
    const storeItem = document.querySelectorAll('.store-item')[index];
    const lat = parseFloat(storeItem.getAttribute('data-latitude'));
    const lng = parseFloat(storeItem.getAttribute('data-longitude'));

    if (!isNaN(lat) && !isNaN(lng)) {
        // Center map on selected location
        map.setCenter({ lat: lat, lng: lng });
        map.setZoom(16);

        // Close all info windows
        closeAllInfoWindows();

        // Open info window for selected marker
        infoWindows[index].open(map, markers[index]);

        // Highlight selected store in list
        highlightStore(index);

        // Add bounce animation
        markers[index].setAnimation(google.maps.Animation.BOUNCE);
        setTimeout(() => {
            markers[index].setAnimation(null);
        }, 750);
    }
}

function closeAllInfoWindows() {
    infoWindows.forEach(infoWindow => {
        infoWindow.close();
    });
}

function highlightStore(index) {
    // Remove highlighting from all stores
    document.querySelectorAll('.store-item').forEach(item => {
        item.classList.remove('active');
    });

    // Add highlighting to selected store
    const selectedStore = document.querySelectorAll('.store-item')[index];
    selectedStore.classList.add('active');
    selectedStore.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}