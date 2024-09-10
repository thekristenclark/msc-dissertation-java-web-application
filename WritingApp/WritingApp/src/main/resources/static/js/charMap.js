// mind_map.js

// Wait for the DOM to be fully loaded before running the script
document.addEventListener('DOMContentLoaded', function() {
    const nodes = new vis.DataSet([
        { id: 1, label: 'Main Character' }
    ]);
    const edges = new vis.DataSet();

    const container = document.getElementById('mynetwork');
    const data = { nodes: nodes, edges: edges };
    const options = {
        manipulation: {
            enabled: true,
            addNode: function(nodeData, callback) {
                nodeData.label = prompt('Enter trait name:') || 'New Trait';
                callback(nodeData);
            },
            editNode: function(nodeData, callback) {
                nodeData.label = prompt('Edit trait name:', nodeData.label);
                callback(nodeData);
            }
        },
        interaction: {
            dragNodes: true,
            dragView: true,
            zoomView: true,
            selectable: true,
            multiselect: true,
        }
    };

    const network = new vis.Network(container, data, options);
    
    
    // Function to convert current network data to JSON
    function getNetworkData() {
        return {
            nodes: nodes.get(),
            edges: edges.get()
        };
    }

    // Save network data to the backend when the save button is clicked
    document.getElementById('submit').addEventListener('click', function() {
        const networkData = getNetworkData();
        fetch('/editor', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(networkData)
        })
        .then(response => response.json())
        .then(data => console.log('Network saved successfully:', data))
        .catch(error => console.error('Error saving network:', error));
    });

    // Load saved network data from the backend when the user logs in
    function loadNetworkData() {
        fetch('/editor') // Use the correct user ID dynamically if needed
            .then(response => response.json())
            .then(data => {
                nodes.clear();
                edges.clear();
                nodes.add(data.nodes);
                edges.add(data.edges);
                network.redraw(); // Redraw the network with the loaded data
            })
            .catch(error => console.error('Error loading network:', error));
    }

    // Automatically load network data on page load
    loadNetworkData();
    
});