let myDiagram; // Declare a global variable for the GoJS diagram
let quillEditors = []; // Array to hold Quill editors

function init() {
    // Initialize GoJS diagram
    myDiagram = new go.Diagram('myDiagramDIV', {
        'undoManager.isEnabled': true, // Enable undo & redo
        'layout': new go.ForceDirectedLayout() // Set layout for the diagram
    });

    // Define the node template
    myDiagram.nodeTemplate = new go.Node(go.Panel.Auto)
        .add(
            new go.Shape('RoundedRectangle', { 
                strokeWidth: 0, 
                fill: 'lightblue' 
            }).bind('fill', 'color')
        )
        .add(
            new go.TextBlock({
                margin: 8,
                font: 'bold 12pt sans-serif',
                editable: true
            }).bind('text', 'key')
        );

    // Define the link template
    myDiagram.linkTemplate = new go.Link({
        routing: go.Link.Orthogonal,
        corner: 5,
        relinkableFrom: true,
        relinkableTo: true
    })
    .add(
        new go.Shape({
            strokeWidth: 1.5,
            stroke: '#000'
        })
    );

    // Load the diagram data
    load();

    // Initialize the Palette
    initPalette();

    // Initialize Quill editors
    initQuillEditors();
}

function load() {
    const savedDiagram = document.getElementById('diagramData').value;
    if (savedDiagram) {
        myDiagram.model = go.Model.fromJson(savedDiagram);
    } else {
        myDiagram.model = new go.GraphLinksModel(
            [
                { key: 'Alpha', color: 'lightblue' }, 
                { key: 'Beta', color: 'lightgreen' }
            ],
            [
                { from: 'Alpha', to: 'Beta' }
            ]
        );
    }
}

function initPalette() {
    const myPalette = new go.Palette('myPaletteDIV', {
        nodeTemplate: myDiagram.nodeTemplate,
        model: new go.GraphLinksModel(
            [
                { key: 'Alpha', color: 'lightblue' },
                { key: 'Beta', color: 'lightgreen' }
            ]
        )
    });
}

function initQuillEditors() {
    // Initialize Quill editors for all elements with class 'quill-editor'
    const editorElements = document.querySelectorAll('.quill-editor');
    editorElements.forEach((element) => {
        const quill = new Quill(element, {
            theme: 'snow', // You can change this to 'bubble' or another theme
            modules: {
                toolbar: [
                    [{ 'header': '1' }, { 'header': '2' }, { 'font': [] }],
                    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                    ['bold', 'italic', 'underline'],
                    ['link'],
                    ['clean']                                         
                ]
            }
        });
        quillEditors.push(quill); // Store editor instances for future reference
    });
}

document.addEventListener('DOMContentLoaded', init);
